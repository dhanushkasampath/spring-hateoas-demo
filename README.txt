
Entity that we use to hateoas should extends ResourceSupport


This will return users which have hyperlinks

in this endpoint we use produces = MediaTypes.HAL_JSON_VALUE


WHAT IS SPRING-HATEOAS?
======================

If we want to expose some rest endpoints to the clients via the REST API's response, we can use hateoas.
Using HATEOAS, you can guide the client with links embedded in the API responses.


EXAMPLE
=======

When a client requests a list of products from /products, your API will return not just the product details,
but also links to:

View each individual product
Add the product to the cart
Proceed to checkout

This can be done by Spring-Hateoas

CODE IMPLEMENTATION
===================

1. First let's create a simple endpoint without hateoas.
   eg:
        Let it to return a list of users as the api response.
2. Then create another endpoint to create links with each user object
   For that User class(our model class) have to extend ResourceSupport class which comes from org.springframework.hateoas.

   But latest version of hateoas does not contain this class. instead we have to use below class
   org.springframework.hateoas.RepresentationModel

3. add a link or links to User objects in the second endpoint we created.


Advantages of Using Spring HATEOAS
==================================

1. Discoverability: Clients can dynamically discover actions available to them (e.g., add to cart, purchase).

2. Decoupling: Reduces the need for clients to hardcode URLs and actions. The API itself provides the next available steps.

3. Better Evolution: When you evolve your API, you can add new links or remove old ones without breaking existing clients.

4. This approach is especially useful when building REST APIs that will be consumed by a variety of clients (web, mobile, etc.) and where the API needs to be flexible to change.



In the HAL response
===================

The _embedded section contains the list of resources (in this case, products).
The _links section contains hypermedia links for each product and for the entire collection.

Why Use HAL?
===========

Standardization: HAL provides a standardized way to represent links in RESTful responses, making it easier for clients to understand and navigate the API.
Discoverability: By embedding links directly in the response, HAL makes APIs more self-descriptive and discoverable.
Interoperability: Clients that support HAL can automatically discover how to interact with the API without additional documentation, improving API usability.

Summary
=======

HAL_JSON_VALUE is a constant for the "application/hal+json" media type, used to indicate that the API will return HAL-encoded JSON.
It allows APIs to include both resource data and hypermedia links, following HATEOAS principles.
HAL JSON is used to make APIs more navigable, standardized, and self-descriptive for clients.