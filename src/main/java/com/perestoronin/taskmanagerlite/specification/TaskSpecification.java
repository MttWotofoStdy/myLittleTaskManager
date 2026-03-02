package com.perestoronin.taskmanagerlite.specification;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import com.perestoronin.taskmanagerlite.entity.Task;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {
    public static Specification<Task> withStatus(TaskStatus status) {
        return (root, query, criteriaBuilder) -> status == null ?
                null : criteriaBuilder.equal(root.get("status"), status);
    }
    public static Specification<Task> withName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null || name.isEmpty() ?
                        null :
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + name.toLowerCase() + "%"
                        );}
    public static Specification<Task> combine(TaskStatus status, String name) {
        List<Specification<Task>> specs = new ArrayList<>();

        if (status != null) {
            specs.add(withStatus(status));
        }
        if (name != null && !name.isEmpty()) {
            specs.add(withName(name));
        }

        if (specs.isEmpty()) {
            return (root, query, cb) -> null;
        }

        Specification<Task> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
