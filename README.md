# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="400" heigth="400" src="https://user-images.githubusercontent.com/101413385/173164615-192ca98a-1a44-480e-9229-9f82f456eec8.png">

</p>

---

## Configuração

Esse projeto utiliza o MySQL 8.0, e providencia um arquivo de configuração do banco de dados para
facilitar a utilização.

Após instalar o MySQL, execute o arquivo `sql_setup.sql`, e tudo será criado. Para verificar se tudo
foi criado corretamente, execute o comando SQL para checar se o administrador `admin` foi criado:

```sql
SELECT ADMIN_ID FROM hotel_alura.tbAdministrator WHERE USERNAME = 'admin';
```

Se o resultado for `1`, então o banco de dados foi configurado corretamente.


