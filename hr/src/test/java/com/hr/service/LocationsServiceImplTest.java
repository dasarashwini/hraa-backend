//package com.hr.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.mapping.Array;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.hr.dao.LocationsRepository;
//import com.hr.dto.LocationsPojo;
//import com.hr.entity.LocationsEntity;
//
//@SpringBootTest
//public class LocationsServiceImplTest {
//	
//	@Mock
//	LocationsRepository locationsRepository;
//	
//	@InjectMocks
//	LocationsServiceImpl locationsServiceImpl;
//	
//	@Test
//	public void testgetAllLocations() {
//		
//		List<LocationsEntity> stubAllLocations= new ArrayList<LocationsEntity>();
//		stubAllLocations.add(new LocationsEntity(200,"Hyderabad",500075,"Kokapet","KK",null));
//		
//		when (locationsRepository.findAll()).thenReturn(stubAllLocations);
//		
//		List<LocationsPojo> expectedAllLocations= new ArrayList<LocationsPojo>();
//		expectedAllLocations.add(new LocationsPojo(200,"Hyderabad",500075,"Kokapet","KK",null));
//
//		
//		List<LocationsPojo>actualAllLocations =locationsServiceImpl.getAllLocations();
//		
//		assertEquals(expectedAllLocations.size(), actualAllLocations.size());
//		verify(locationsRepository).findAll();
//	}
//	
//	@Test
//	public void 
//	
//
//}
