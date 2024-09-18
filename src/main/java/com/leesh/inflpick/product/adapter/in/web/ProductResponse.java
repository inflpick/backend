package com.leesh.inflpick.product.adapter.in.web;

import java.util.List;

public record ProductResponse(String name,
                              String description,
                              List<String> tags) {
}
