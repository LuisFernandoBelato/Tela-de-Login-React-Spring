'use client';
import Cookies from 'js-cookie';

export async function loginUser() 
{
  let emailElement = document.getElementById("LoginEmail") as HTMLInputElement | null;
  let passwordElement = document.getElementById("LoginPassword") as HTMLInputElement | null;

  if (emailElement && passwordElement && emailElement.value != "" && passwordElement.value != "")
  {
    let email: string = emailElement.value;
    let password: string = passwordElement.value;

    let json: object = {
      email: email,
      password: password
    };

    console.log(json);

    // READY TO FETCH

    let URL = `http://localhost:8080/apis/security/login`
    let response = await fetch(URL, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(json)
    });
    if (response.ok)
    {
      let data = await response.json();
      console.log(data);
      Cookies.set('token', data.token, { expires: 7 });
      alert("Você Logou no Sistema! Seu nível de Acesso é : "+data.level);
    }
    else
    {
      alert("Seu Login Falhou! Verifique se Errou algum CAMPO!");
      //throw new Error("Something goes wrong");
    }
    
      let formElement = document.getElementById("FormLoginID") as HTMLFormElement | null;
      if (formElement)
        formElement.reset();
  }
  else
    alert("Preencha os Campos que estão VAZIOS!");
} 

export async function RegisterNewUser ()
{
  let name = document.getElementById("RegName") as HTMLInputElement | null;
  let email = document.getElementById("RegEmail") as HTMLInputElement | null;
  let cpf : any = document.getElementById("RegCpf") as HTMLInputElement | null;
  let dt_nasc = document.getElementById("RegDt_Nasc") as HTMLInputElement | null;
  let address = document.getElementById("RegAddress") as HTMLInputElement | null
  let password = document.getElementById("RegPassword") as HTMLInputElement | null;


  if (name && email && password && cpf && dt_nasc && address &&
    name.value != "" && email.value != "" && password.value != "" &&
    cpf.value != "" && dt_nasc.value != "" && address.value != ""
    && cpf.content == "validated")
  {  
    let CPF : string = cpf.value;
    CPF = CPF.replace(".","");
    CPF = CPF.replace("-","");
    CPF = CPF.replace(".","");

    let json : object = {
      name: name.value,
      email: email.value,
      cpf: CPF,
      password: password.value,
      address: address.value,
      dt_nasc: dt_nasc.value 
    }

    console.log(json);

    // READY TO FETCH

    let URL : string = `http://localhost:8080/apis/security/register-user`;
    let response = await fetch(URL, {
      "method": "POST",
      "body": JSON.stringify(json),
      "headers": {"Content-Type": "application/json"}
    })
    if (response.ok)
    {
      let data : object = response.json();
      console.log(data);
      alert("Cadastrado Com Sucesso!");
    }
    else
    {
      alert("Falha ao Registrar Novo Usuário");
      //throw new Error("Falied to register user");
    }

    let form = name.parentNode as HTMLFormElement | null;
    if (form)
      form.reset();

  }
  else
  {
    if (cpf.content != "validated")
      alert("CPF Inválido! Verifique Novamente.");
    else
      alert("Preencha os Campos que estão VAZIOS!");
  }

}

////////////////////////////////////////////////////////////////

// MASKS AND VALIDATIONS



export function mCpf(event : any) {
  var cpf = event.target.value;
  if (cpf.length > 11)
    event.target.value = cpf = cpf.substring(0, 14);
  cpf = cpf.replace(/\D/g, "")
  cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
  cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
  cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
  event.target.value = cpf;
}


export function validateCPF(event: any)
{
  let cpf = event.target.value;
  let ok = 1;
  let add: number;
  let i: number;
  let rev: number;

  if (cpf !== "") {
    cpf = cpf.replace(/[^\d]+/g, '');
    if (
      cpf.length !== 11 ||
      cpf === "00000000000" ||
      cpf === "11111111111" ||
      cpf === "22222222222" ||
      cpf === "33333333333" ||
      cpf === "44444444444" ||
      cpf === "55555555555" ||
      cpf === "66666666666" ||
      cpf === "77777777777" ||
      cpf === "88888888888" ||
      cpf === "99999999999"
    ) {
      ok = 0;
    }

    if (ok === 1) {
      add = 0;
      for (i = 0; i < 9; i++) {
        add += parseInt(cpf.charAt(i)) * (10 - i);
      }
      rev = 11 - (add % 11);
      if (rev === 10 || rev === 11) {
        rev = 0;
      }
      if (rev !== parseInt(cpf.charAt(9))) {
        ok = 0;
      }

      if (ok === 1) {
        add = 0;
        for (i = 0; i < 10; i++) {
          add += parseInt(cpf.charAt(i)) * (11 - i);
        }
        rev = 11 - (add % 11);
        if (rev === 10 || rev === 11) {
          rev = 0;
        }
        if (rev !== parseInt(cpf.charAt(10))) {
          ok = 0;
        }
      }
    }

    if (ok === 0) {
      alert("Ops... Ocorreu um problema... CPF inválido!");
      event.target.content = "not validated";
      // event.target.focus();
    }
    else
      event.target.content = "validated";
  }
}

