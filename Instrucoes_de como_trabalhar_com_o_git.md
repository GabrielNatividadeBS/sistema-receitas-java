###NUNCA EDITE A MAIN DIRETAMENTE !
-----

## O Fluxo de Trabalho Completo (Do Início ao Fim)

Imagine que você vai criar uma nova funcionalidade, como uma "tela de busca de receitas". Aqui está o passo a passo:

### Passo 1: Antes de Começar (Sincronizando com o Ramo Principal)

Antes de criar qualquer coisa nova, garanta que seu ambiente local está 100% atualizado com a versão mais recente do projeto.

1.  **Vá para o ramo `main`:**

    ```bash
    git checkout main
    ```

2.  **Puxe as últimas atualizações do GitHub:** (Mesmo que só você trabalhe no projeto, isso garante que você está partindo da versão correta).

    ```bash
    git pull origin main
    ```

### Passo 2: Criando seu Ramo de Trabalho (A "Branch")

Agora que você está atualizado, crie um "ramo" novo, um espaço de trabalho isolado para sua nova funcionalidade.

1.  **Crie e já mude para a nova branch:** O comando `-b` cria a branch e já te move para ela.
    ```bash
    git checkout -b feature/tela-de-busca
    ```
      * **Dica de Nomenclatura:** É uma boa prática nomear branches com um prefixo como `feature/` para novas funcionalidades ou `fix/` para correção de bugs. Ex: `fix/erro-conexao-banco`.

### Passo 3: Trabalhando e Salvando seu Progresso (`add` e `commit`)

Agora você está na branch `feature/tela-de-busca`. Pode programar à vontade, sabendo que não está afetando o ramo `main`.

1.  Faça suas alterações: crie novos arquivos, edite os existentes, etc.
2.  Quando tiver um progresso significativo que queira salvar, faça um `commit`:
    ```bash
    # Adiciona todos os arquivos modificados para o "pacote" do commit
    git add .

    # Salva o "pacote" com uma mensagem clara sobre o que você fez
    git commit -m "Adiciona campo de busca e botão na tela principal"
    ```
      * Você pode fazer **quantos commits quiser** na sua branch. É bom fazer commits pequenos e frequentes.

### Passo 4: Enviando seu Ramo para o GitHub (`push`)

Quando sua funcionalidade estiver pronta (ou quando você quiser que outros a vejam), envie sua branch para o repositório no GitHub.

1.  **Faça o push da sua nova branch:**
    ```bash
    git push -u origin feature/tela-de-busca
    ```
      * O `-u` é usado na primeira vez que você envia a branch. Ele cria um "link" entre sua branch local e a remota. Nas próximas vezes, você pode usar apenas `git push`.

### Passo 5: Abrindo o Pull Request (O seu "MR")

Agora que sua branch está no GitHub, você precisa pedir para que ela seja "juntada" (merged) ao ramo `main`. Isso é feito através de um **Pull Request**.

1.  Vá para a página do seu repositório no GitHub.
2.  O GitHub provavelmente mostrará uma faixa amarela com o nome da sua branch e um botão **"Compare & pull request"**. Clique nele.
3.  Se a faixa não aparecer, vá na aba **"Pull requests"** e clique em **"New pull request"**.
4.  Escreva um **título** claro para seu PR (ex: "Implementa a Tela de Busca de Receitas").
5.  Na descrição, explique o que você fez.
6.  Clique em **"Create pull request"**.

### Passo 6: Juntando Tudo (`Merge`) e Limpando a Casa

Seu Pull Request está aberto. Em um time, outra pessoa revisaria seu código. Como está sozinho, você será o revisor.

1.  Na página do Pull Request, se tudo estiver certo, clique no botão verde **"Merge pull request"** e confirme.
2.  **Pronto\!** Seu código da branch `feature/tela-de-busca` agora faz parte do ramo `main`.
3.  O GitHub oferecerá um botão **"Delete branch"**. É uma boa prática deletar a branch depois que ela foi integrada, para manter o repositório limpo.

-----

### Resumo do Ciclo Completo para o Dia a Dia

```bash
# 1. Garante que seu main local está atualizado
git checkout main
git pull origin main

# 2. Cria um novo ramo para trabalhar
git checkout -b nome-da-nova-feature

# 3. Trabalha... (cria e edita arquivos)
# ...
git add .
git commit -m "Fiz uma parte da feature"
# ...
git add .
git commit -m "Terminei a feature"

# 4. Envia o ramo para o GitHub
git push -u origin nome-da-nova-feature

# 5. Vai no site do GitHub e abre o Pull Request (PR)

# 6. Depois de mergear o PR no site, volte para o passo 1 para começar um novo trabalho!
```
