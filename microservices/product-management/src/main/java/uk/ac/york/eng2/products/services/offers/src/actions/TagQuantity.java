package uk.ac.york.eng2.products.services.offers.src.actions;

import io.micronaut.core.annotation.NonNull;

import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagQuantity {


    private final TagRepository tagRepository;

    private final List<Tag> tags;
    private final List<String> tagNames;
    private final Integer quantity;

    public List<Tag> getTags() { return tags; }
    public List<String> getTagNames() { return tagNames; }
    public Integer getQuantity() { return quantity; }


    public TagQuantity(List<String> tagNames, Integer quantity, TagRepository tagRepository) {
        this.tagNames = tagNames;
        this.quantity = quantity;
        this.tags = new ArrayList<>();
        this.tagRepository = tagRepository;
    }


    public boolean process() {
        for (String tagName : tagNames) {
            @NonNull Optional<Tag> oTag = tagRepository.findByName(tagName);
            if (oTag.isEmpty()) {
                return false;
            }
            Tag tag = oTag.get();
            this.tags.add(tag);
        }
        return true;
    }

}
