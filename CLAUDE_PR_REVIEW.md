
## Claude Code Review Setup

Ce projet utilise Claude pour réviser automatiquement toutes les pull requests vers la branche `main`.

### Configuration requise

1. **Installer l'application Claude GitHub** : Allez sur [https://github.com/apps/claude-ai](https://github.com/apps/claude-ai) et installez l'application sur ce repository.

2. **Ajouter votre clé API Anthropic** : 
   - Allez dans Settings → Secrets and variables → Actions
   - Créez un nouveau secret nommé `ANTHROPIC_API_KEY`
   - Ajoutez votre clé API Anthropic (disponible sur [console.anthropic.com](https://console.anthropic.com))

3. **Utilisation** : Une fois configuré, Claude révisera automatiquement toutes les nouvelles PR vers `main`. Vous pouvez aussi mentionner `@claude` dans les commentaires de PR pour poser des questions spécifiques.