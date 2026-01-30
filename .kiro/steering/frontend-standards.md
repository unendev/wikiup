# Frontend Development Standards

## Code Organization

### Directory Structure
```
src/
├── components/      # Reusable UI components
├── views/           # Page-level components (routes)
├── router/          # Vue Router configuration
├── stores/          # Pinia stores for state management
├── services/        # API service layer
├── composables/     # Reusable composition functions
├── types/           # TypeScript type definitions
├── assets/          # Static assets (images, fonts)
└── App.vue          # Root component
```

## Coding Standards

### Vue 3 Composition API
- Use `<script setup>` syntax for all components
- Prefer Composition API over Options API
- Extract reusable logic into composables
- Use `ref` for primitive values, `reactive` for objects
- Properly type all props and emits

### TypeScript
- Enable strict mode
- Define interfaces for all data structures
- Avoid `any` type - use `unknown` if type is truly unknown
- Use type inference where possible
- Export types from dedicated `types/` directory

### Component Design
- Keep components focused and single-purpose
- Use props for parent-to-child communication
- Use emits for child-to-parent communication
- Use Pinia stores for global state
- Prefer composition over inheritance

### Styling
- Use Tailwind CSS utility classes
- Follow mobile-first responsive design
- Use Tailwind's design tokens (colors, spacing, etc.)
- Extract repeated patterns into components
- Use `@apply` sparingly in component styles

### State Management (Pinia)
- Create separate stores for different domains (auth, chat, etc.)
- Use composition store syntax with `setup()`
- Keep stores focused on specific concerns
- Use getters for derived state
- Actions should handle async operations

### Routing
- Define routes in `router/index.ts`
- Use route guards for authentication
- Implement lazy loading for route components
- Use named routes for navigation
- Handle 404 with catch-all route

### API Services
- Centralize API calls in `services/` directory
- Use axios interceptors for auth tokens
- Handle errors consistently
- Type all request/response data
- Use environment variables for API base URL

### Form Handling
- Use VeeValidate for form validation
- Define validation schemas
- Show clear error messages
- Disable submit during processing
- Handle API errors gracefully

## Best Practices

### Performance
- Use `v-show` for frequently toggled elements
- Use `v-if` for conditionally rendered elements
- Implement virtual scrolling for long lists
- Lazy load routes and heavy components
- Optimize images and assets

### Accessibility
- Use semantic HTML elements
- Add proper ARIA labels
- Ensure keyboard navigation works
- Maintain sufficient color contrast
- Test with screen readers

### Code Quality
- Use ESLint and follow its recommendations
- Format code consistently
- Write self-documenting code
- Add comments for complex logic only
- Keep functions small and focused

## Build & Run

### Development
```bash
cd frontend
npm run dev
```

### Build for Production
```bash
npm run build
```

### Preview Production Build
```bash
npm run preview
```

## Environment Variables
Create `.env` file for environment-specific configuration:
```
VITE_API_BASE_URL=http://localhost:8080
```
