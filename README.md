# Galette Sausage Monorepo

A Monorepo for B.I.G.S: Breton Infamous Galette Sausage  

A presentation to know beter state of AI coding assistant in 2025, and begin too work on a realist project with Claude Code.

Not sure of my anglishe here 

## Quick Start

```bash
# Install dependencies
pnpm install

# Start all services (DB, Backend, Frontend)
pnpm run start:all

# Or start services individually:
pnpm run db:start      # Start PostgreSQL database
pnpm run backend:dev   # Start Spring Boot backend (port 8080)
pnpm run frontend:dev  # Start Astro frontend (port 4321)

# Stop database
pnpm run stop:all
```

## Services

- **Database**: PostgreSQL on port 5432
- **Backend**: Spring Boot on http://localhost:8080/api
- **Frontend**: Astro on http://localhost:4321

