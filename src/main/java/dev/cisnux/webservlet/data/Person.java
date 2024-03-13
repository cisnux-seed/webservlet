package dev.cisnux.webservlet.data;

import lombok.With;


@With
public record Person(String email, String username) {
}
