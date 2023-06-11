package org.medmota.demo.entities;

import java.util.Date;
import java.util.UUID;

public record Product(UUID id, Date creationDate, String imagePath) {

}
