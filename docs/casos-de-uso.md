# ESPECIFICAÇÃO DOS CASOS DE USO

## 1. CADASTRAR LISTA DE COMPRAS
### Nome: Caso de Uso - Cadastrar Lista de Compras
### Ator Principal: Cliente
### Resumo: Este caso de uso permite que o cliente castre produtos em uma lista de comprar na plataforma.
### Pré-Condições: 
* Cliente cadastrado no sistema 
### Pós-Condições: 
* Consulta da lista de compras
#### Fluxo Principal:
##### Ações do ator
1. __Digita o nome produto
2. __Seleciona a marca do produto
#### Ações do Sistema
3. **O sistema busca na base de dados pelas palavras-chave relacionadas ao tipo de produto**
4. **O sistema retorna os produtos cadastrados e mostra nas informações**

#### Fluxo Alternativo:
##### __Ações do Ator__ **Ações do Sistema**
1. __Digita o nome produto__
2. __Efetua a pesquisa do produto__
#### Ações do Sistema
3. **O sistema busca na base de dados pelas palavras-chave relacionadas ao tipo de produto**
4. **O sistema retorna uma mensagem informando que não há produtos relacionados à pesquisa.**

## 2. INCLUIR ROTA
### Nome: Caso de Uso - Incluir rota
### Ator Principal: Cliente
### Resumo: Este caso de uso permite que o cliente trace uma rota até o supermercado mais próximo
### Pré-Condições: 
* Cliente cadastrado no sistema
* Sistema disponibilizar os produtos cadastrados
### Pós-Condições:
* Chegada ao mercado economizando tempo
#### Fluxo Principal:
##### __Ações do Ator__
1. __Cliente seleciona o mercado escolhido __ 
#### Ações do Sistema
2. **O sistema traça a rota que leve o cliente pelo menor tempo ao mercado**

## 3. CADASTRO DE MERCADO
### Nome: Caso de Uso - Cadastrar mercado
### Ator Principal: Administrador do mercado
### Resumo: Este caso de uso é feito pelo administrador do mercado, cadastrando o mercado
### Pré-Condições: 
* Ter um banco de dados com os produtos e preços
### Pós-Condições:
* Cadastro do mercado é feito
#### Fluxo Principal:
##### __Ações do Ator__ 
1. __Cadastrar marcado no sistema__
2. __Incluir banco de dados com produtos e preços no sistema.__
#### Ações do Sistema
3. __O sistema retorna que o cadastro foi concluido

