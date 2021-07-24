package com.example.springbootrestservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.springbootrestservice.controller.AddResponse;
import com.example.springbootrestservice.controller.LibraryController;
import com.example.springbootrestservice.entity.Library;
import com.example.springbootrestservice.repository.LibraryRepository;
import com.example.springbootrestservice.service.LibraryService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SpringbootrestserviceApplicationTests {
	@Autowired
	LibraryController con;

	@MockBean
	LibraryRepository repository;
	@MockBean
	LibraryService libraryService;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkBuildIDLogic(){
		LibraryService service = new LibraryService();
		String id = service.buildId("isbn", "aisle");
		assertEquals("isbnaisle", id);
	}

	@Test
	public void addBookTest(){
		// mock the dependencies
		Library lib = buildLibrary();
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());
		
		when(libraryService.checkBookAlreadyExist(lib.getId())).thenReturn(false);
		ResponseEntity response	=con.addBookImplementation(buildLibrary());//step
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);

		AddResponse ad= (AddResponse) response.getBody();
		ad.getId();
		assertEquals(lib.getId(),ad.getId());
		assertEquals("Success: Book has been Added",ad.getMsg());
	}

	public Library buildLibrary()
	{
		Library lib =new Library();
		lib.setAisle("322");
		lib.setBook_name("Spring");
		lib.setIsbn("sfe");
		lib.setAuthor("Rahul Shetty");
		lib.setId("sfe3b");
		return lib;
		
	}

}
