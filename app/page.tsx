"use client"; 

import { useState } from "react";
import { loginUser, mCpf, RegisterNewUser, validateCPF } from './Service/functions';
import { register } from "module";


export default function LoginAndRegisterScreen() {
  return (
      <SquareToLoginOrRegister />
  );
}



function SquareToLoginOrRegister ()
{
  const [register, setRegister] = useState(false);

  return (
  <>
  <div className="flex items-center justify-center min-h-screen">
    <div className="w-full max-w-md bg-white p-6 rounded-lg shadow-lg">
      {register == false ? 
        <FormLogin register={register} setRegister={setRegister}/>
          :
        <FormRegister register={register} setRegister={setRegister}/>
      }
    </div>
    </div>
  </>
  );
}


function FormLogin (props : any)
{
  return (
    <>
      <h2 className="text-2xl font-bold mb-4">Tela de Login</h2>
      <form id="FormLoginID">
        <label className="block mb-3">Seu Email</label>
        <input id="LoginEmail" type="email" placeholder="Ex. MeuEmail@gmail.com" className="block mb-2 border border-gray-300 px-3 py-2 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />

        <label className="block mb-3">Sua Senha</label>
        <input id="LoginPassword" type="password" placeholder="XXXXXXXX" className="block mb-2 border border-gray-300 px-3 py-2 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />

        <div className="flex items-center mb-5 mt-5">
          <label className="mr-4">Lembrar de Mim</label>
          <input type="checkbox" className="text-sm font-medium" />
        </div>

        <button type="button" onClick={loginUser} className="block mt-4 bg-blue-500 text-white font-bold py-2 px-4 rounded w-full hover:bg-blue-700">Login</button>
        
        <p className="block mt-10 text-sm">Não Possui uma Conta ainda ? <a onClick={() => props.setRegister(!props.register)} target="_blank" className="text-blue-600 cursor-pointer">Cadastre-se</a></p>  
      </form>
    </>
  );
}



function FormRegister (props : any)
{
  return (
    <>
      <h2 className="text-2xl font-bold mb-4">Tela de Cadastro</h2>
      <form id="FormRegisterID">
      <label className="block mb-3">Seu Nome</label>
          <input id="RegName" type="text" placeholder="Ex. Adailton Soares Junior" className="block mb-2 border border-gray-300 px-2 py-1 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />
          
          <label className="block mb-3">Seu CPF</label>
          <input id="RegCpf" onChange={mCpf} onBlur={validateCPF} content="not validated" type="text" placeholder="XXX.XXX.XXX-XX" className="block mb-2 border border-gray-300 px-2 py-1 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />

          <label className="block mb-3">Seu Email</label>
          <input id="RegEmail" type="email" placeholder="Ex. MeuEmail@gmail.com" className="block mb-2 border border-gray-300 px-2 py-1 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />
          
          <label className="block mb-3">Data de Nascimento</label>
          <input id="RegDt_Nasc" type="date" className="block mb-2 border border-gray-300 px-2 py-1 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />
        
          <label className="block mb-3">Endereço</label>
          <input id="RegAddress" type="text" placeholder="Ex. Estado - Cidade - Bairro - Rua - Número" className="block mb-2 border border-gray-300 px-2 py-1 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />

          <label className="block mb-3">Sua Senha</label>
          <input id="RegPassword" type="password" placeholder="XXXXXXXX" className="block mb-2 border border-gray-300 px-2 py-1 w-full rounded-md shadow-sm focus:outline-none focus:border-blue-500" />

          <button type="button" onClick={RegisterNewUser} className="block mt-4 bg-blue-500 text-white font-bold py-2 px-4 rounded w-full hover:bg-blue-700">Cadastrar</button>

          <button type="button" onClick={() => props.setRegister(!props.register)} className="absolute top-5 left-7 bg-gray-300 text-white font-bold py-1 px-2 rounded hover:bg-gray-800">Voltar</button>
      </form>
    </>
  );
}