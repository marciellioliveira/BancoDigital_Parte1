# BancoDigital_Parte1
Projeto Final do Educ360° Parte 1 - Desenvolvimento de um Banco Digital em java para console e sem persistência de dados.

Apresentação do Projeto no Youtube: https://www.youtube.com/watch?v=jwnL02cOuic&ab_channel=TecGirl

### O Projeto

Você montou uma startup que desenvolve softwares para clientes. Seu primeiro cliente é de uma instituição financeira e quer um software na web que seja capaz de cadastrar dados de seus clientes como CPF, Nome, Data de nascimento e Endereço. Os clientes podem ser subdivididos em três categorias: Comum, Super e Premium e podem abrir contas de dois tipos: corrente e poupança, devendo estas realizar ações básicas, como exibir saldo e fazer transferências via Pix. A conta corrente possui uma taxa mensal de manutenção que deve ser descontada a cada mês e a conta poupança deve fazer o acréscimo conforme a taxa de rendimento no momento.

### Regras de Negócio

1. **Cadastro de Clientes:** O sistema deve permitir o cadastro de novos clientes com CPF, nome, data de nascimento e endereço, classificando-os em categorias Comum, Super ou Premium.
2. **Contas Bancárias:** Os clientes podem abrir contas correntes e/ou poupança, cada uma com funcionalidades específicas:
   - Conta Corrente: Deve ter uma taxa de manutenção mensal descontada automaticamente.
   - Conta Poupança: Deve creditar mensalmente o rendimento conforme a taxa vigente.
3. **Operações Bancárias:** Implementar funcionalidades para exibição de saldo, transferências via Pix, e outras operações básicas.
4. **Cartões de Crédito e Débito:** Emitir cartões vinculados às contas dos clientes, permitindo operações de pagamento, alteração de senha, ativação/desativação e ajuste de limites.
     - Cartão de Crédito: Deve ter um limite pré-aprovado e bloquear novos pagamentos ao atingir o limite.
     - Cartão de Débito: Deve ter um limite diário de transações, podendo ser ajustado pelo usuário.
5. **Seguros de Cartão de Crédito:** Oferecer produtos de seguro específicos para cartões de crédito, gerando apólices eletrônicas com detalhes sobre cobertura e condições.
  
### Validação de Campos na Inserção do Usuário: 

1. **CPF:** - Deve ser único para cada cliente. Validar o formato (xxx.xxx.xxx-xx) e a autenticidade usando um algoritmo de validação de CPF.
2. **Nome:** - Deve conter apenas letras e espaços. Comprimento mínimo de 2 caracteres e máximo de 100 caracteres.
3. **Data de Nascimento:** - Deve ser uma data válida no formato DD/MM/AAAA . O cliente deve ser maior de idade (≥ 18 anos) com base na data atual.
4. **Endereço:** - Deve incluir rua, número, complemento (se aplicável), cidade, estado e CEP. Verificar se o formato do CEP é válido (xxxxx-xxx).

### Contas Bancárias e Taxas: 

1. **Conta Corrente:**
   - **Taxa de Manutenção Mensal:** R$ 12,00 para clientes Comuns, R$ 8,00 para clientes Super e isenta para clientes Premium.
2. **Conta Poupança:** - **Taxa de Rendimento Anual:** 0,5% ao ano para clientes Comuns, 0,7% ao ano para clientes Super, e 0,9% ao ano para clientes Premium. O rendimento é calculado mensalmente usando a fórmula do juro composto, baseando-se no saldo presente na conta no último dia do mês.

### Cartões: 

- **Cartão de Crédito:** e **Limite de Crédito:**
Deve ser definido com base no cliente, 1 mil (COMUM), 5 mil (SUPER), 10 mil (PREMIUM). 
- **Taxa de Utilização:** 5% sobre o total gasto no mês, aplicável apenas se o total de gastos exceder 80% do limite de crédito.

### Seguros de Cartão de Crédito:
- **Seguro Viagem:** Gratuito para clientes Premium; opcional para clientes Comum e Super, com taxa de R$ 50,00 por mês.
- **Seguro de Fraude:** Cobertura automática para todos os cartões, com um valor de apólice base de R$ 5.000,00.

- 
