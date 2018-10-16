# ESPECIFICAÇÃO DOS CASOS DE USO

## 1. CADASTRAR CLIENTE
### Nome: Caso de Uso - Cadastrar Cliente
### Ator Principal: Cliente
### Resumo: Este caso de uso permite que o cliente faça o cadastro na plataforma
### Pré-Condições: 
* Cliente ser pessoa física
### Pós-Condições:
* Acesso a plataforma de lista de compras
#### Fluxo Principal:
##### __Ações do Ator e do sistema
1. __Cliente clica em cadastrar
2. __Sistema retorna a tela de cadastro que contem nome, email, telefone e preferencias
3. __O ator realiza o cadastro
2. __O sistema retorna mensagem de cadastro realizado com sucesso e abre plataforma


## 2. INCLUIR ITENS NA LISTA DE COMPRAS
### Nome: Caso de Uso - Incluir itens na lista de Compras
### Ator Principal: Cliente
### Resumo: Este caso de uso permite que o cliente adicione produtos em uma lista de comprar na plataforma.
### Pré-Condições: 
* Cliente cadastrado no sistema 
### Pós-Condições: 
* Consulta da lista de compras
#### Fluxo Principal:
##### Ações do ator e do sistema
1. __o ator digita o nome produto
2. __O sistema busca na base de dados pelas palavras-chave relacionadas ao tipo de produto
3. __O ator seleciona o produto
4. __O sistema retorna os produtos cadastrados e as informações.


#### Fluxo Alternativo:
##### __Ações do Ator e do Sistema
1. __O ator digita o nome produto__
2. __O sistema busca na base de dados pelas palavraschave relacionadas ao tipo de produto
3. __O ator efetua a pesquisa do produto
4. __O sistema retorna uma mensagem informando que não há produtos relacionados a pesquisa




## 3. MARCAR ITEM COMO COMPRADO
### Nome: Caso de Uso - Marcar item como comprado
### Ator Principal: Cliente
### Resumo: Este caso de uso permite que o cliente retire o item da lista de compras quanto o pegar no mercado
### Pré-Condições: 
* ter uma lista de compras cadastrada
### Pós-Condições:
* item fica em vermelho
#### Fluxo Principal:
##### __Ações do Ator e do Sistema
1. __O ator ao pegar um item no mercado que corresponda a lista de compras, toca no item na lista
2. __O sistema retorna o item em vermelho
