# Claude and The Galette-Sausage Projet

## Product

### Main purpose

### MVP

- backend features :
  - public routes:
    - get sausages choices, toppings choices, prices
  - private routes
    - login/sigup (only mail + password, no further check except required-valid email-strong password backend validation, no recover, JWT token) for consumers
    - current basket CRUD for each registered consumer
    - order list for each consumer
- frontend feature
  - home
  - login/signup form (no check except required-valid email-strong password, frontend validation, same than backend)
  - basket: contains current unpaid galettes-sausages added by logged in consumer
    - when user click on "pay", it automatically considerit paid, basket becomes a past order and is empty, ready for next order
    - order time is registered as well, and delivery will occurs in 30 minutes from now
  - past orders of logged in consumer

### Nice to have
- our kitchen have an address: 2 Rue de la Mabilais, 35000 Rennes
- when a user register he now have to enter:
  - a name, that we will use
  - an address: it MUST be less than 15km from ours, or can’t register, checked on backend and frontend
- the delivery time of order is now indexed by kilometers to run in bicyle (prevision based on kilometers)



## Technical Decisions 


### Stack: 
  - Monorepo: PNPM Workspace
  - DB: PostgreSQL via Docker
  - Backend: Java Spring Boot
  - Frontend: Astro
  - Auth: JWT Token via Spring and Bearer

### Naming
- galette-sausage-monorepo
- frontend project: apps/order-webapp-backend
- backend project: apps/order-webapp


### Quality
  - API local test: http file
  - 80% test coverage
  - No comment in code (except crazy hacks)
  - No crazy hack
  

### Versionning and Delivery:
  - Github, Github action


### Hosting: 
  - DNS:AWS route 53 
   HTTPS: / Cloudfront
  - DB, Java Backend, Static Astro Pages: render.com
  - Warning: mandatory variable naming
  - Backend: don’t forget DB and CORS conf (env.production)
  - Frontend: don’t forget public api conf (apps/order-webapp-frontend/.env.production)


### Environments:
  - Local: db on docker, local pnpm, local java
  - Prod: render.com components

