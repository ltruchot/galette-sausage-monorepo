/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{astro,html,js,jsx,md,mdx,svelte,ts,tsx,vue}'],
  theme: {
    extend: {
      colors: {
        'galette': {
          50: '#fdf8f3',
          100: '#faeee1',
          200: '#f5dcc2',
          300: '#edc299',
          400: '#e4a36e',
          500: '#dc8850',
          600: '#ce6d3e',
          700: '#ac5434',
          800: '#8a4530',
          900: '#703a29',
        }
      }
    },
  },
  plugins: [],
}