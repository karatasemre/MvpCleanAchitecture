package emre.com.cleanarchitecturetutorial.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    private List<Item> items;

    @JsonCreator
    public Items(@JsonProperty("results") List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
