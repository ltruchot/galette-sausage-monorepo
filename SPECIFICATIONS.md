# Product

## Main Purpose

Deliver Galette-Sausage all over town
- Frontend to order Galette-Sausage
- Backend to receive and save orders, transmit to kitchen and deliverer

## Business Rules
- Raw Galette-sausage at 4€/unit, contains either:
  - 1 classic pork sausage
  - 1 lamb/beef merguez
  - 1 vegetarian sausage
- Can add 0 to 2 sauces, 1 time each, 50cts/unit:
  - Mustard
  - Ketchup
  - Mayonnaise
  - Algerian
- Can add 0 to 3 toppings, 1 or 2 times each, 1€/unit:
  - Cheese
  - Salad
  - Pickles
  - Fried onions
  - Jalapeño peppers
- Then add to basket, can order another and so on
- Then pay to place order, and wait for delivery

## MVP

- Backend features:
  - DB populate on launch, if not already
  - Public endpoints:
    - Get list/prices for raw galette-sausage, sauces, toppings
  - Private endpoints:
    - Login/signup (only email + password, no further check except required-valid email-strong password backend validation, no recovery, JWT token) for consumers
    - Current basket CRUD for each registered consumer
    - Current order list for each consumer
  - Basic Error handling
    
- Frontend features:
  - Header/Footer (login state and other info)
  - Home: title, cool explanation prensentig product, invitation to login/signup or order, or view current orders 
  - Login/signup form (no check except required-valid email-strong password, frontend validation, same as backend)
  - Consumer form to order:
    - Implements business rules
    - 1 select mono-choice for raw galette-sausage
    - Multi-choice select or buttons for sauces and toppings
    - Button to save in basket
  - Basket: contains current unpaid galettes-sausages added by logged-in consumer
    - When user clicks "pay", it automatically considers it paid, basket becomes a past order and is empty, ready for next order
    - Order time is registered as well, and delivery will occur in 30 minutes from now
  - Past orders of logged-in consumer
  - Basic api errors toaster or message

## Nice to Have
- One random button to generate a crazy galette
- One "best seller", based on all user orders (only one, if no winner, display last winner)
- 1 galette free for 3+ galettes
- Our kitchen has an address: 2 Rue de la Mabilais, 35000 Rennes
- When a user registers, they now have to enter:
  - A name that we will use
  - An address: it MUST be less than 15km from ours, or can't register, checked on backend and frontend
- The delivery time of orders is now indexed by kilometers to run by bicycle (prediction based on kilometers)

## Technical Decisions

## Stack:
- Monorepo: PNPM Workspace
- DB: PostgreSQL via Docker
- Backend: Java Spring Boot
- Frontend: Astro
- Auth: JWT Token via Spring and Bearer

## Quality
- API local test: HTTP file
- 80% test coverage
- well formatted and linted
- No comments in code (except crazy hacks)
- No crazy hacks

## Versioning and Delivery:
- GitHub
- Render.com watch and pull repos

## Hosting:
- DB, Java Backend, Static Astro Pages: render.com
- Backend: don't forget DB and CORS config (env.production)
- Frontend: don't forget public API config (apps/order-webapp-frontend/.env.production)
- DNS / HTTPS: AWS Route 53 / CloudFront 

## Environments:
- Local: DB on Docker, local pnpm, local Java
- Prod: render.com services



