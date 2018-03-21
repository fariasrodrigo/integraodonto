/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setaAlteraProfissional(id, contato, nome, sexo, esp, cpf, rg, stats, agenda, celular, fixo, email, login, senha, nivel) {
    document.getElementById('myLargeModalLabel').textContent = 'Alterando Profissional : ' + nome;
    document.getElementById('profissionalId').value = id;
    document.getElementById('profissionalContatoID').value = contato;
    document.getElementById('profissionalNome').value = nome;
    document.getElementById('profissionalSexo').value = sexo;
    document.getElementById('profissionalEspecializacao').value = esp;
    document.getElementById('profissionalCpf').value = cpf;
    document.getElementById('profissionalRg').value = rg;
    document.getElementById('profissionalStats').value = stats;
    document.getElementById('profissionalAgenda').value = agenda;
    document.getElementById('profissionalCelular').value = celular;
    document.getElementById('profissionalFixo').value = fixo;
    document.getElementById('profissionalEmail').value = email;
    document.getElementById('profissionalLogin').value = login;
    document.getElementById('profissionalSenha').value = senha;
    document.getElementById('profissionalNivel').value = nivel;
}

function setaPerfilTodosPacientes(nome, prontuario, nascimento, cpf, rg, celular, fixo, email, cep, end, numero, compl, bairro, cidade, estado) {
    document.getElementById('myLargeModalLabel').textContent = 'Informações de ' + nome;
    document.getElementById('pacienteprontuario').textContent = prontuario;
    document.getElementById('pacientenascimento').textContent = nascimento;
    document.getElementById('pacientecpf').textContent = cpf;
    document.getElementById('pacienterg').textContent = rg;
    document.getElementById('pacientecelular').textContent = celular;
    document.getElementById('pacientefixo').textContent = fixo;
    document.getElementById('pacienteemail').textContent = email;
    document.getElementById('pacientecep').textContent = 'CEP: ' + cep;
    document.getElementById('pacienteendereco').textContent = end +', '+ numero + ', ' + compl + ', ' + bairro + ', ' + cidade + '-' + estado;
}

function setaAlteraAgendamento(id, paciente, data, inicio, fim, profissional, obs, stats, sms, email) {
    document.getElementById('alteraId').value = id;
    document.getElementById('alteraPaciente').value = paciente;
    document.getElementById('alteraData').value = data;
    document.getElementById('alteraInicio').value = inicio;
    document.getElementById('alteraFim').value = fim;
    document.getElementById('alteraProfissional').value = profissional;
    document.getElementById('alteraObs').value = obs;
    document.getElementById('alteraStats').value = stats;
    document.getElementById('alteraSms').value = sms;
    document.getElementById('alteraEmail').value = email;
}

function setaAlteraProcedimento(id, nome, valor, obs, stats) {
    document.getElementById('procedimentoId').value = id;
    document.getElementById('procedimentoNome').value = nome;
    document.getElementById('procedimentoStats').value = stats;
    document.getElementById('procedimentoValor').value = valor;
    document.getElementById('procedimentoObs').value = obs;
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