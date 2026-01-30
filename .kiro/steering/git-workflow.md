# Git Workflow & Commit Standards

## Branch Strategy

### Main Branches
- `main` - Production-ready code
- `develop` - Integration branch for features

### Feature Branches
- Create from: `develop`
- Naming: `feature/description` (e.g., `feature/user-authentication`)
- Merge to: `develop`

### Bugfix Branches
- Create from: `develop` or `main`
- Naming: `bugfix/description` (e.g., `bugfix/login-error`)
- Merge to: `develop` or `main`

### Hotfix Branches
- Create from: `main`
- Naming: `hotfix/description` (e.g., `hotfix/security-patch`)
- Merge to: `main` and `develop`

## Commit Message Format

Use conventional commits format:
```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, no logic change)
- `refactor`: Code refactoring
- `perf`: Performance improvements
- `test`: Adding or updating tests
- `chore`: Build process or auxiliary tool changes
- `ci`: CI/CD configuration changes

### Examples
```
feat(auth): add JWT token refresh mechanism

Implement automatic token refresh before expiration
to improve user experience and reduce login frequency.

Closes #123
```

```
fix(chat): resolve WebSocket connection timeout

Increase connection timeout and add retry logic
for unstable network conditions.
```

```
docs(readme): update installation instructions
```

## Code Review Guidelines

### Before Submitting PR
- Ensure code builds without errors
- Run tests and verify they pass
- Update documentation if needed
- Remove debug code and console logs
- Check for sensitive data in commits

### PR Description Should Include
- What changes were made
- Why the changes were necessary
- How to test the changes
- Screenshots for UI changes
- Related issue numbers

### Reviewing Code
- Check for code quality and standards compliance
- Verify logic correctness
- Look for potential bugs or edge cases
- Ensure proper error handling
- Check for security vulnerabilities
- Verify test coverage

## Files to Never Commit

Already in `.gitignore`, but be extra careful:
- `.env` files with secrets
- `node_modules/`
- `target/` (Maven build output)
- IDE-specific files (`.idea/`, `.vscode/` settings)
- Log files
- Database files
- Compiled binaries
- Temporary files
