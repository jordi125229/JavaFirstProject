package repository;

import resources.Resource;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryResourceRepository implements ResourceRepository {
    private List<Resource> resources;
    private int resourcesCount;

    public InMemoryResourceRepository() {
        this.resources = new ArrayList<>();
        this.resourcesCount = 0;
    }

    @Override
    public void add(Resource resource) {
        resources.add(resource);
        resourcesCount++;
    }

    @Override
    public Optional<Resource> findByName(String name) {
        for (Resource resource : resources) {
            if (resource.getName().equalsIgnoreCase(name)) {
                return Optional.of(resource);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Resource> findAllResources() {
        if (resourcesCount == 0) {
            System.out.println("No resources");
        }
        return resources;
    }
}
