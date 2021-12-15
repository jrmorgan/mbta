package mbta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mbta.mapper.MbtaMapper;
import mbta.model.Mbta;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TestMbtaMapper {
    @Test
    void emptyMbta() throws JsonProcessingException {
        String json = "{\"data\": [], \"included\": []}";
        JsonNode node = new ObjectMapper().readTree(json);

        Mbta expected = new Mbta(Collections.emptySet(), Collections.emptySet());

        assertEquals(expected, new MbtaMapper().createMbta(node));
    }

    @Test
    void createMbtaSingleRoute() throws JsonProcessingException, IOException {
        File jsonFile = new File("src/test/resources/testBlueLine.json").getAbsoluteFile();
        JsonNode node = new ObjectMapper().readTree(jsonFile);

        Mbta expected = new TestMbtaFixture().getMbta(TestMbtaFixture.Route.BLUE);

        assertEquals(expected, new MbtaMapper().createMbta(node));
    }

    @Test
    void createMbtaSingleRouteWithTwoPaths() throws JsonProcessingException, IOException {
        File jsonFile = new File("src/test/resources/testRedLine.json").getAbsoluteFile();
        JsonNode node = new ObjectMapper().readTree(jsonFile);

        Mbta expected = new TestMbtaFixture().getMbta(TestMbtaFixture.Route.RED);

        assertEquals(expected, new MbtaMapper().createMbta(node));
    }

    @Test
    void createMbtaThreeLines() throws JsonProcessingException, IOException {
        File jsonFile = new File("src/test/resources/testMbta.json").getAbsoluteFile();
        JsonNode node = new ObjectMapper().readTree(jsonFile);

        Mbta expected = new TestMbtaFixture().getMbta(TestMbtaFixture.Route.ALL);

        assertEquals(expected, new MbtaMapper().createMbta(node));
    }
}
