package com.solomon.solomon.shared.dtos;

import java.util.List;

public record ListOutputDTO<T>(Integer count, List<T> data) {
}
