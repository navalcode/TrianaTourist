package com.salesianos.triana.dam.TrianaTourist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianos.triana.dam.TrianaTourist.models.*;
import com.salesianos.triana.dam.TrianaTourist.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties={"spring.datasource.url=jdbc:h2:mem:tourist_review;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE","spring.h2.console.enabled=false"})
@AutoConfigureMockMvc
class TouristHttpJourneyTest {
    @Autowired MockMvc mvc; @Autowired ObjectMapper json;
    @Autowired CategoryRepository categories; @Autowired PoiRepository pois; @Autowired RouteRepository routes;
    Category category; Category other; Poi poi; Poi second; Route route;
    final long missing=Long.MAX_VALUE;
    @BeforeEach void fixture() {
        category=categories.save(Category.builder().name("Category-"+UUID.randomUUID()).build());
        other=categories.save(Category.builder().name("Other-"+UUID.randomUUID()).build());
        poi=pois.save(Poi.builder().name("Fictional point").location("0,0").category(category).build());
        second=pois.save(Poi.builder().name("Another point").location("1,1").category(category).build());
        route=routes.save(new Route("Route-"+UUID.randomUUID(),new ArrayList<>(List.of(poi,second))));
    }
    String routeBody(String name,List<Long> ids) throws Exception { Map<String,Object> b=new HashMap<>();b.put("name",name);b.put("ids",ids);return json.writeValueAsString(b); }
    String poiBody(String location) throws Exception {Map<String,Object>b=new HashMap<>();b.put("name","Edited point");b.put("location",location);b.put("cat_id",other.getId());b.put("coverPhoto","https://images.example.invalid/cover.png");return json.writeValueAsString(b);}
    @Test void missingRouteEditNamesRoute() throws Exception {
        mvc.perform(put("/route/{id}",missing).contentType("application/json").content(routeBody("Missing-"+UUID.randomUUID(),List.of(poi.getId())))).andExpect(status().isNotFound()).andExpect(jsonPath("$.mensaje",containsString("Route")));
    }
    @Test void missingRouteDeleteNamesRoute() throws Exception {mvc.perform(delete("/route/{id}",missing)).andExpect(status().isNotFound()).andExpect(jsonPath("$.mensaje",containsString("Route")));}
    @Test void poiEditUsesRequestedCategory() throws Exception {
        Category requested=other.getId().equals(poi.getId())?category:other;
        Map<String,Object> body=json.readValue(poiBody("0,0"),Map.class);body.put("cat_id",requested.getId());
        mvc.perform(put("/poi/{id}",poi.getId()).contentType("application/json").content(json.writeValueAsString(body))).andExpect(status().isOk()).andExpect(jsonPath("$.category.id").value(requested.getId()));
        assertEquals(requested.getId(),pois.findById(poi.getId()).orElseThrow().getCategory().getId());
    }
    @Test void malformedCoordinatesAre400WithoutPersistence() throws Exception {
        long before=pois.count();for(String location:Arrays.asList(null,"invalid","91,0","0,181","0,0,0"))mvc.perform(post("/poi/").contentType("application/json").content(poiBody(location))).andExpect(status().isBadRequest());assertEquals(before,pois.count());
    }
    @Test void nullRouteIdsAre400() throws Exception {mvc.perform(post("/route/").contentType("application/json").content(routeBody("Null-"+UUID.randomUUID(),null))).andExpect(status().isBadRequest());}
    @Test void nullNameAndIdElementAre400() throws Exception {
        mvc.perform(post("/route/").contentType("application/json").content(routeBody(null,List.of(poi.getId())))).andExpect(status().isBadRequest());
        mvc.perform(post("/route/").contentType("application/json").content(routeBody("Null-id-"+UUID.randomUUID(),Arrays.asList((Long)null)))).andExpect(status().isBadRequest());
    }
    @Test void duplicateRouteStepsAre400WithoutWrite() throws Exception {long before=routes.count();mvc.perform(post("/route/").contentType("application/json").content(routeBody("Duplicate-"+UUID.randomUUID(),List.of(poi.getId(),poi.getId())))).andExpect(status().isBadRequest());assertEquals(before,routes.count());}
    @Test void appendDuplicateIs400AndKeepsTwoSteps() throws Exception {mvc.perform(put("/route/{r}/{p}",route.getId(),poi.getId())).andExpect(status().isBadRequest());mvc.perform(get("/route/{id}",route.getId())).andExpect(status().isOk()).andExpect(jsonPath("$.steps.length()").value(2));}
    @Test void routeEditMayKeepOwnNameButNotAnotherRoutesName() throws Exception {
        mvc.perform(put("/route/{id}",route.getId()).contentType("application/json").content(routeBody(route.getName(),List.of(second.getId())))).andExpect(status().isOk()).andExpect(jsonPath("$.steps[0]").value(second.getId()));
        Route otherRoute=routes.save(new Route("Reserved-"+UUID.randomUUID(),new ArrayList<>()));
        mvc.perform(put("/route/{id}",route.getId()).contentType("application/json").content(routeBody(otherRoute.getName(),List.of(poi.getId())))).andExpect(status().isBadRequest());
        assertEquals(route.getName(),routes.findById(route.getId()).orElseThrow().getName());
    }
    @Test void categoryEditMayKeepOwnName() throws Exception {mvc.perform(put("/category/{id}",category.getId()).contentType("application/json").content(json.writeValueAsString(Map.of("name",category.getName())))).andExpect(status().isOk());mvc.perform(put("/category/{id}",category.getId()).contentType("application/json").content(json.writeValueAsString(Map.of("name",other.getName())))).andExpect(status().isBadRequest());assertEquals(category.getName(),categories.findById(category.getId()).orElseThrow().getName());}
    @Test void malformedJsonKeeps400() throws Exception {mvc.perform(post("/route/").contentType("application/json").content("{")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.codigo").value(400));}
    @Test void unsupportedMethodKeeps405() throws Exception {mvc.perform(patch("/route/{id}",route.getId())).andExpect(status().isMethodNotAllowed()).andExpect(header().exists("Allow"));}
    @Test void createReadAndDeletePointPreserveOtherRouteSteps() throws Exception {
        String response=mvc.perform(post("/route/").contentType("application/json").content(routeBody("Created-"+UUID.randomUUID(),List.of(poi.getId(),second.getId())))).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        long id=json.readTree(response).get("id").asLong();
        mvc.perform(get("/route/{id}",id)).andExpect(status().isOk()).andExpect(jsonPath("$.steps.length()").value(2));
        mvc.perform(delete("/poi/{id}",poi.getId())).andExpect(status().isNoContent());
        mvc.perform(get("/route/{id}",id)).andExpect(status().isOk()).andExpect(jsonPath("$.steps.length()").value(1)).andExpect(jsonPath("$.steps[0]").value(second.getId()));
    }
}
