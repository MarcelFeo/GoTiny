package com.example.GoTiny.Links.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GoTiny.Links.Link;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
}
