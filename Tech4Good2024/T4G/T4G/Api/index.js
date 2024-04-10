const express = require('express');
const app = express();
const sql =  require('./sql.js');
var moment = require('moment');
app.use(express.json());
app.use(express.urlencoded({extended:true}));
const Port = 9990;

app.get('/test', async(req, res)=>{
    res.send(await sql.executeQuery());
})

app.get('/lstEstados', async (req, res) => {
    res.send(await sql.Select(`Select * from tblEstado`));
});

app.get('/lstEventos', async (req, res) => {
    res.send(await sql.Select(`Select * from tblEvento`));
});

app.get('/lstRoles', async (req, res) => {
    res.send(await sql.Select(`Select * from tblRoles`));
});

app.get('/lstLogin', async (req, res) => {
    res.send(await sql.Select(`Select * from tblLogin`));
});

app.get('/lstUsers', async (req, res) => {
    res.send(await sql.Select(`Select * from tblUser`));
});

app.post('/Account/Login', async(req, res) =>{
    let user = ""+req.body.Username, pass = ""+req.body.Password;

    let a = await sql.SelectCompare(`Select * from tblLogin where usern like '${user}' and passw like '${pass}'`);
    let s = a != -1? `O/A utilizadora ${user} fez login com sucesso -${a}-!!`: `Algum dado invalido...`;
    res.send(s);
});

app.post('/Account/RegistarEvento', async(req, res)=>{
    let idEstado = req.body[0].idEstado, idLogin = req.body[0].idLogin, idEvento = req.body[0].idEvento;

    await sql.PutValues(`Insert into tblDetalhesEventos(idEventos, idUser, idEstado) values(${idEvento},${idLogin},(Select idLogin from tblUser where idLogin = ${idLogin}))`)
    console.log("");
});

app.post('/Account/REgist', async(req, res) => {
    let nomeAPelido = ""+req.body.txtNomeApelido, email = ""+req.body.txtEmail, pass = ""+req.body.txtPass, Nascimento = ""+req.body.txtNascimento;

    let a = await sql.PutValues(`Insert into tblLogin(usern, passw) values('${email}', '${pass}')`);
    if(a)
        a = await sql.PutValues(`Insert into tblUser(idRole, Nome, Apelido, Email, Nascimento, ContaCriada) values(4, '${nomeAPelido.split(' ')[0]}','${nomeAPelido.split(' ')[1]}', '${email}', '${Nascimento}', '${moment().format('YYYY-MM-DD HH:mm:ss')}')`);

    if(a)
        res.send("Com sucesso!!");
    else
        res.send("Sem sucesso!!");
});



app.listen(Port, (req, res) =>{
    sql.connection();
    console.log(`Servidor rodando na porta ${Port}`);
})