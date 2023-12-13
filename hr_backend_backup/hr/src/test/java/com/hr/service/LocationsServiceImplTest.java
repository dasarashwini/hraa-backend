package com.hr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.hr.dao.LocationsRepository;
import com.hr.dto.CountriesPojo;
import com.hr.dto.LocationsPojo;
import com.hr.dto.RegionsPojo;
import com.hr.entity.CountriesEntity;
import com.hr.entity.LocationsEntity;
import com.hr.entity.RegionsEntity;
import com.hr.exceptions.ResourceAlreadyExistsException;
import com.hr.exceptions.ResourceNotFoundException;

@SpringBootTest
public class LocationsServiceImplTest {
	
	@Mock
	LocationsRepository locationsRepository;
	
	@InjectMocks
	LocationsServiceImpl locationsServiceImpl;
	
	@Test
	public void testAddLocations() {
	    LocationsPojo newLocation = new LocationsPojo(200, "Hyderabad", "500075", "Kokapet", "TS",
	            new CountriesPojo("AR", "Argentia", new RegionsPojo(10, "Europe")));
	    LocationsEntity expectedEntity = new LocationsEntity(200, "Hyderabad", "500075", "Kokapet", "TS",
	            new CountriesEntity("AR", "Argentia", new RegionsEntity(10, "Europe")));

	    when(locationsRepository.findById(200)).thenReturn(Optional.empty());
	    when(locationsRepository.save(any(LocationsEntity.class))).thenReturn(expectedEntity);

	    String result = locationsServiceImpl.addLocations(newLocation);
	    assertEquals("Locations added successfully", result);

	    verify(locationsRepository, times(1)).findById(200);
	    verify(locationsRepository, times(1)).save(any(LocationsEntity.class));
	}

	
	@Test
    public void testAddLocationsException() {
        // Given
        LocationsPojo newLocation = new LocationsPojo(200, "Hyderabad", "500075", "Kokapet", "TS",
                new CountriesPojo("AR", "Argentia", new RegionsPojo(10, "Europe")));

        // Stubbing the findById method to return an empty Optional, indicating that the location does not exist
        when(locationsRepository.findById(200)).thenReturn(Optional.empty());

        // Stubbing the save method to return the expected LocationsEntity
        LocationsEntity expectedEntity = new LocationsEntity();
        BeanUtils.copyProperties(newLocation, expectedEntity);
        when(locationsRepository.save(any(LocationsEntity.class))).thenReturn(expectedEntity);

        // When
        String result = locationsServiceImpl.addLocations(newLocation);

        // Then
        assertEquals("Locations added successfully", result);

        // Verify that findById was called once with the specified locationId
        verify(locationsRepository, times(1)).findById(200);

        // Verify that save was called once with any LocationsEntity argument
        verify(locationsRepository, times(1)).save(any(LocationsEntity.class));
    }
	@Test
    public void testUpdateLocationsException() {
        // Given
        LocationsPojo newLocation = new LocationsPojo(200, "Hyderabad", "500075", "Kokapet", "TS",new CountriesPojo("AR", "Argentia", new RegionsPojo(10, "Europe")));

        // Stubbing the findById method to return an Optional containing the specified location
        when(locationsRepository.findById(newLocation.getLocationsId())).thenReturn(Optional.empty());

        // Stubbing the save method to return the saved location
        when(locationsRepository.save(any(LocationsEntity.class))).thenReturn(new LocationsEntity());

        // When
        String result = locationsServiceImpl.updateLocations(newLocation);

        // Then
        assertEquals("Locations updated successfully", result);

        // Verify that findById was called once with the specified locationId
        verify(locationsRepository, times(1)).findById(newLocation.getLocationsId());

        // Verify that save was called once with the specified location entity
        verify(locationsRepository, times(1)).save(any(LocationsEntity.class));
    }

    @Test
    public void testUpdateLocationsAlreadyExists() {
        // Given
        LocationsPojo existingLocation = new LocationsPojo(200, "Hyderabad", "500075", "Kokapet", "TS",new CountriesPojo("AR", "Argentia", new RegionsPojo(10, "Europe")));

        // Stubbing the findById method to return an Optional containing the specified existing location
        when(locationsRepository.findById(existingLocation.getLocationsId())).thenReturn(Optional.of(new LocationsEntity()));

        // When and Then
        ResourceAlreadyExistsException exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            locationsServiceImpl.updateLocations(existingLocation);
        });

        assertEquals("Location with ID 200 Already exists", exception.getMessage());

        // Verify that findById was called once with the specified locationId
        verify(locationsRepository, times(1)).findById(existingLocation.getLocationsId());

        // Verify that save was not called
        verify(locationsRepository, never()).save(any(LocationsEntity.class));
    }

	
	@Test
	public void testgetAllLocations() {
		
		List<LocationsEntity> stubAllLocations= new ArrayList<LocationsEntity>();
		stubAllLocations.add(new LocationsEntity(200,"Hyderabad", "500075","Kokapet", "TS", new CountriesEntity("AR", "Argentina", new RegionsEntity(10,"Europe"))));

		
		when (locationsRepository.findAll()).thenReturn(stubAllLocations);
		
		List<LocationsPojo> expectedAllLocations= new ArrayList<LocationsPojo>();
		expectedAllLocations.add(new LocationsPojo(200,"Hyderabad", "500075","Kokapet", "TS", new CountriesPojo("AR", "Argentina", new RegionsPojo(10,"Europe"))));
	
		List<LocationsPojo>actualAllLocations =locationsServiceImpl.getAllLocations();
		
		assertEquals(expectedAllLocations.size(), actualAllLocations.size());
		verify(locationsRepository).findAll();
	}
	
	@Test
	public void testGetAllLocationsException() {
		// create mockito rule
		List<LocationsEntity> stubAllLocations = new ArrayList<LocationsEntity>();
		when(locationsRepository.findAll()).thenReturn(stubAllLocations);
		
		// get the expected output
		String expectedMessage = "locations is not avilable";
		
		// get actual output
		ResourceNotFoundException actualException = assertThrows(ResourceNotFoundException.class,()->locationsServiceImpl.getAllLocations()); 
		String actualMessage = actualException.getMessage();
		
		// assert the expected and actual output
		assertEquals(expectedMessage, actualMessage);
		verify(locationsRepository).findAll();
		
	}
	
	@Test
	public void testgetLocationsById() {
	    int id = 200;

	    LocationsEntity locationsEntity = new LocationsEntity(
	            200, "Hyderabad", "500075", "Kokapet", "TS",
	            new CountriesEntity("AR", "Argentia", new RegionsEntity(10, "Europe"))
	    );

	    when(locationsRepository.findById(id)).thenReturn(Optional.of(locationsEntity));

	    LocationsPojo result = locationsServiceImpl.getLocationsById(id);

	    System.out.println("Expected LocationsId: " + id);
	    System.out.println("Actual LocationsId: " + result.getLocationsId());

	    assertEquals(id, result.getLocationsId());


	    verify(locationsRepository).findById(id);
	}

	@Test
    public void testGetLocationsByIdNotFoundException() {
        // Given
        int nonExistentLocationsId = 999;

        // Stubbing the findById method to return an empty Optional, indicating that the location does not exist
        when(locationsRepository.findById(nonExistentLocationsId)).thenReturn(Optional.empty());

        // When and Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            locationsServiceImpl.getLocationsById(nonExistentLocationsId);
        });

        assertEquals("Location with id 999 not exists", exception.getMessage());

        // Verify that findById was called once with the specified non-existent locationId
        verify(locationsRepository, times(1)).findById(nonExistentLocationsId);
    }
	
	 @Test
	    public void testGetLocationsByIdException() {
	        // Given
	        int locationsId = 200;
	        LocationsEntity locationsEntity = new LocationsEntity(200, "Hyderabad", "500075", "Kokapet", "TS",new CountriesEntity("AR", "Argentia", new RegionsEntity(10, "Europe")));

	        // Stubbing the findById method to return an Optional containing the specified location
	        when(locationsRepository.findById(locationsId)).thenReturn(Optional.of(locationsEntity));

	        // When
	        LocationsPojo result = locationsServiceImpl.getLocationsById(locationsId);

	        // Then
	        LocationsPojo expectedPojo = new LocationsPojo();
	        BeanUtils.copyProperties(locationsEntity, expectedPojo);
	        CountriesPojo countriesPojo = new CountriesPojo();
	        BeanUtils.copyProperties(locationsEntity.getCountriesEntity(), countriesPojo);
	        expectedPojo.setCountriesPojo(countriesPojo);

	        assertEquals(expectedPojo, result);

	        // Verify that findById was called once with the specified locationId
	        verify(locationsRepository, times(1)).findById(locationsId);
	    }
		
		@Test
		public void testdeleteLocationsById() {
			
			LocationsEntity stubLocationsEntity=new LocationsEntity(200, "Hyderabad", "500075", "Kokapet", "TS", new CountriesEntity("AR", "Argentia", new RegionsEntity(10, "Europe")));
			Optional<LocationsEntity> OptionalLocationsEntityList=Optional.of(stubLocationsEntity);
			
			when(locationsRepository.findById(200)).thenReturn(OptionalLocationsEntityList);
			String msge = locationsServiceImpl.deleteLocationsById(200);
			verify(locationsRepository, times(1)).findById(200);
			verify(locationsRepository, times(1)).deleteById(200);
			
			assertEquals("locations deleted successfully", msge);
		
		}
		
		@Test
	    public void testDeleteLocationsByIdException() {
	        int locationsId = 1;

	        LocationsEntity locationsEntity = new LocationsEntity(locationsId,"Hyderabad", "500075","Kokapet", "TS", new CountriesEntity("AR", "Argentina", new RegionsEntity(10,"Europe")));
	        Optional<LocationsEntity> optionalLocationsEntity = Optional.of(locationsEntity);

	        when(locationsRepository.findById(locationsId)).thenReturn(optionalLocationsEntity);

	       
			String result = locationsServiceImpl.deleteLocationsById(locationsId);

	        assertEquals("locations deleted successfully", result);

	        verify(locationsRepository).deleteById(locationsId);
	        verify(locationsRepository).findById(locationsId);
	    }		
		
	}

