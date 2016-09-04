package com.nu.mi.reset;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import de.laliluna.example.Honey;
import de.laliluna.example.TestExample;

@Api(value = "/tokenservice", description = "This service provide a rest service for providing a valid token")
@Path("/honey")
public class ResFulService  {
	@GET
	@Path("/{ownerID}")
	@ApiOperation(value = "Get all the honey ", notes = "get all the honey ", response = Response.class)
	public Response get(
			@ApiParam(value = "  Id of honey to fetch", required = true) @PathParam("ownerID") String id) {
		JSONArray list = new TestExample().listHoney();
		return Response.status(Response.Status.OK).entity(list).build();

	} 
	
	@POST
	@Path("/{name}/{taste}")
	@ApiOperation(value = "create honey ", notes = "create honey ", response = Response.class)
	public Response create(
			@ApiParam(value = "  Name of the honey", required = true) @PathParam("name") String name,
			@ApiParam(value = "  taste of the honey", required = true) @PathParam("taste") String taste) {
		
		
		 	Honey forestHoney = new Honey();
		    forestHoney.setName(name);
		    forestHoney.setTaste(taste);
		    new TestExample().createHoney(forestHoney);
		    
		return Response.status(Response.Status.OK).entity("{created}").build();

	} 
}
