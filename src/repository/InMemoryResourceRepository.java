package repository;

import resources.Resource;

import java.util.List;
import java.util.Optional;

public class InMemoryResourceRepository implements ResourceRepository {
    private List<Resource> resources;
    private int resourcesCount = 0;

    @Override
    public void add(Resource r) {
        resources.add(r);
        resourcesCount++;
    }

    @Override
    public Optional<Resource> findByName(String name) {
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
