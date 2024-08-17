package com.solomon.solomon.shared.dtos;

public record ListInputDTO(Integer page, Integer limit, String search) {

    private static Integer MAX_LIMIT = 20;

    public ListInputDTO {
        if (search.isEmpty()) {
            search = "";
        }

        if (limit > MAX_LIMIT) {
            limit = MAX_LIMIT;
        }
    }
}
