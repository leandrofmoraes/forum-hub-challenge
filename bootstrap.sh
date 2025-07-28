#!/usr/bin/env bash

# bootstrap.sh
# Script de inicialização do workflow para o projeto forum-hub

set -euo pipefail

PROJECT_DIR="$HOME/workspace/forum-hub"

echo "[1/5] Iniciando serviço Docker..."
sudo systemctl start docker

echo "[2/5] Subindo container do banco de dados…"
docker-compose -f "$PROJECT_DIR/docker-compose.yml" up -d

echo "[4/5] Criando sessão tmux 'project'…"
tmux new-session -d -s project -c "$PROJECT_DIR"

echo "[3/5] Configurando variáveis de ambiente.."
MYSQL_USER=$(grep -oP '(?<=MYSQL_USER=).*' "$PROJECT_DIR/.env")
MYSQL_PASSWORD=$(grep -oP '(?<=MYSQL_PASSWORD=).*' "$PROJECT_DIR/.env")
JWT_SECRET="123456"

if [[ -z "$MYSQL_USER" ]]; then
  echo "Erro: MYSQL_USER não encontrado no arquivo .env."
  exit 1
fi
if [[ -z "$MYSQL_PASSWORD" ]]; then
  echo "Erro: MYSQL_PASSWORD não encontrado no arquivo .env."
  exit 1
fi

tmux set-environment -t project MYSQL_USER "$MYSQL_USER"
tmux set-environment -t project MYSQL_PASSWORD "$MYSQL_PASSWORD"
tmux set-environment -t project JWT_SECRET "$JWT_SECRET"

echo "  ➜ Janela 'root'…"
tmux new-window -t project: -n root -c "$PROJECT_DIR"

echo "  ➜ Janela 'code'…"
tmux new-window -t project: -n code -c "$PROJECT_DIR/src/main/java/com/forumhub/api/"

echo "  ➜ Janela 'db'…"
tmux new-window -t project: -n db -c "$PROJECT_DIR"
tmux send-keys -t project:db "docker exec -it forumhub-db mysql -u leandro -p" C-m

echo "  ➜ Janela 'client'…"
tmux new-window -t project: -n client -c "$PROJECT_DIR/requests/"

echo "[5/5] Conectando à sessão tmux 'project'…"
tmux attach -t project
