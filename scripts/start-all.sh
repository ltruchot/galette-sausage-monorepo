#!/bin/bash

echo "🚀 Starting Galette-Sausage Services..."
echo "======================================="

# Start database
echo "📦 Starting PostgreSQL database..."
docker-compose up -d

# Wait for database to be ready
echo "⏳ Waiting for database to be ready..."
sleep 3

# Start backend and frontend in parallel
echo "🔧 Starting backend and frontend services..."
echo ""

# Use concurrently with custom labels
npx concurrently \
  --names "BACKEND,FRONTEND" \
  --prefix-colors "bgGreen.bold,bgBlue.bold" \
  "cd apps/order-webapp-backend && mvn spring-boot:run -Dspring-boot.run.profiles=local" \
  "cd apps/order-webapp-frontend && pnpm dev"