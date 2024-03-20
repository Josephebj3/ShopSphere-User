package com.cogent.main;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name 
{
	private String firstName;
	private String lastName;
}
