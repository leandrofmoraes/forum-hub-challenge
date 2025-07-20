#!/usr/bin/env bash
# Script de inicialização do workflow para o projeto forum-hub

set -euo pipefail

# Diretório do projeto
PROJECT_DIR="$HOME/workspace/forum-hub"

# 1. Iniciar o Docker
echo "[1/4] Iniciando serviço Docker..."
sudo systemctl start docker

# 2. Subir o container do banco de dados
echo "[2/4] Subindo container do banco de dados…"
docker-compose -f "$PROJECT_DIR/docker-compose.yml" up -d

# 3. Criar sessão tmux 'project'
echo "[3/4] Criando sessão tmux 'project'…"
# Janela 'root' na pasta raiz do projeto
tmux new-session -d -s project -c "$PROJECT_DIR" -n root

# Janela 'code' no diretório Java
echo "  ➜ Janela 'code'…"
tmux new-window -t project: -n code -c "$PROJECT_DIR/src/main/java/com/forumhub/api/"

# Janela 'db' para acessar o MySQL dentro do container
echo "  ➜ Janela 'db'…"
tmux new-window -t project: -n db -c "$PROJECT_DIR"
tmux send-keys -t project:db "docker exec -it forumhub-db mysql -u leandro -p" C-m

echo "  ➜ Configurando variáveis de ambiente.."
MYSQL_USER=$(grep -oP '(?<=MYSQL_USER=).*' "$PROJECT_DIR/.env")
MYSQL_PASSWORD=$(grep -oP '(?<=MYSQL_PASSWORD=).*' "$PROJECT_DIR/.env")

if [[ -z "$MYSQL_USER" ]]; then
  echo "Erro: MYSQL_USER não encontrado no arquivo .env."
  exit 1
fi
if [[ -z "$MYSQL_PASSWORD" ]]; then
  echo "Erro: MYSQL_PASSWORD não encontrado no arquivo .env."
  exit 1
fi

export MYSQL_USER
export MYSQL_PASSWORD

# Janela 'client' para requisições via curl
echo "  ➜ Janela 'client'…"
tmux new-window -t project: -n client -c "$PROJECT_DIR/requests/"

# 4. Anexar à sessão
echo "[4/4] Conectando à sessão tmux 'project'…"
tmux attach -t project
