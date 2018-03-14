/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setaAlteraProfissional(id, nome, sexo, esp, cpf, rg, stats, agenda, login, senha, nivel) {
    document.getElementById('myLargeModalLabel').textContent = 'Alterando Profissional : ' + nome;
    document.getElementById('profissionalId').value = id;
    document.getElementById('profissionalNome').value = nome;
    document.getElementById('profissionalSexo').value = sexo;
    document.getElementById('profissionalEspecializacao').value = esp;
    document.getElementById('profissionalCpf').value = cpf;
    document.getElementById('profissionalRg').value = rg;
    document.getElementById('profissionalStats').value = stats;
    document.getElementById('profissionalAgenda').value = agenda;
    document.getElementById('profissionalLogin').value = login;
    document.getElementById('profissionalSenha').value = senha;
    document.getElementById('profissionalNivel').value = nivel;
}

if (!window.sendPost) {
    window.sendPost = function (url, obj) {
        //Define o formulário
        var myForm = document.createElement("form");
        myForm.action = url;
        myForm.method = "post";

        for (var key in obj) {
            var input = document.createElement("input");
            input.type = "text";
            input.value = obj[key];
            input.name = key;
            myForm.appendChild(input);
        }
        //Adiciona o form ao corpo do documento
        document.body.appendChild(myForm);
        //Envia o formulário
        myForm.submit();
    };
}