# Claude and The Galette-Sausage Project

Aka B.I.G.S: Breton Infamous Galette Sausage

## About Me and Disclaimer
- Loïc TRUCHOT from Zeemutable, Shodo contractor
- Simple front-end developer, knows nothing, learns every day
- English bad, tyypinng bad, Mac bad
- AI tool early adopter, not an expert
- NO GUARANTEE INCLUDED

## About AI
- AI already saves (and destroys) lives, too late
- Today, AI consumes 500 TWh
  - 2030: 1500 TWh, India's current consumption, 5% of world consumption, IEA says
  - In 2050: 3500 TWh, singularity is coming
- AI steals jobs and makes you dumb

But,
- Just like the internet in general
- AI already improves 20 to 40% productivity AND efficiency/discovery rate in most domains it's applied: weather, research, medicine, smart homes and cities, administration, police, military, education...
- For coders, it's even more than 50%
- A full day chatting with ChatGPT uses 300,000 tokens, same CO2 as a Galette-Sausage, "all included"
- 90% of developers install an AI tool while installing an IDE
- 80% used it last week
- 50% of generated code is kept as is
- Big question: "Is it worth it?"

Let's try together with Claude Code

## About Claude Code
- 2014: Bing Code Search
- 2018: Microsoft buys GitHub
- 2019: Microsoft archives Codeballs
- 2020: Oege de Moor trains GPT3 on it
- 2021: GitHub Copilot is announced: what about copyright?
- 2021: Split in OpenAI, Dario Amodei creates Anthropic
- 2022: Copyright problem is sealed, Copilot is now a paid product
- 2023: Cursor, Claude: GitLab/Bitbucket/GitHub open sourced training
- 2024: Windsurf, Claude 3, Anthropic invents MCP
- 2025: Claude Code, a CLI agentic coding tool
- Written in TypeScript (lol)
- 80% written by itself (we.are.already.dead)

## Sources (French mixed, sorry)

- [Anthropic CPO interview - Lenny's Podcast](https://www.lennysnewsletter.com/p/anthropics-cpo-heres-what-comes-next)
- [Anthropic CEO interview - Lex Friedman](https://www.youtube.com/watch?v=ugvHCXCOmm4)
- [The Conversation](https://app.theconversation.fr/deeplink/article?article_id=260218&type=article)
- [Le Grand Continent](https://legrandcontinent.eu/fr/2024/07/13/lia-fait-exploser-la-consommation-denergie/)
- [Fortune Business Insights](https://www.fortunebusinessinsights.com/industry-reports/artificial-intelligence-market-100114)
- [State of AI 2025](https://www.qodo.ai/reports/state-of-ai-code-quality/)
- Claude AI, sourced

## Product

### Main Purpose

Deliver Galette-Sausage all over town
- Frontend to order Galette-Sausage
- Backend to receive and save orders, transmit to kitchen and deliverer

### Business Rules
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

### MVP

- Backend features:
  - DB populate on launch, if not already
  - Public routes:
    - Get list/prices for raw galette-sausage, sauces, toppings
  - Private routes:
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

### Nice to Have
- One random button to generate a crazy galette
- One "best seller", based on all user orders (only one, if no winner, display last winner)
- 1 galette free for 3+ galettes
- Our kitchen has an address: 2 Rue de la Mabilais, 35000 Rennes
- When a user registers, they now have to enter:
  - A name that we will use
  - An address: it MUST be less than 15km from ours, or can't register, checked on backend and frontend
- The delivery time of orders is now indexed by kilometers to run by bicycle (prediction based on kilometers)

## Technical Decisions

### Stack:
- Monorepo: PNPM Workspace
- DB: PostgreSQL via Docker
- Backend: Java Spring Boot
- Frontend: Astro
- Auth: JWT Token via Spring and Bearer

### Quality
- API local test: HTTP file
- 80% test coverage
- well formatted and linted
- No comments in code (except crazy hacks)
- No crazy hacks

### Versioning and Delivery:
- GitHub
- Render.com watch and pull repos

### Hosting:
- DB, Java Backend, Static Astro Pages: render.com
- Backend: don't forget DB and CORS config (env.production)
- Frontend: don't forget public API config (apps/order-webapp-frontend/.env.production)
- DNS / HTTPS: AWS Route 53 / CloudFront 

### Environments:
- Local: DB on Docker, local pnpm, local Java
- Prod: render.com services



