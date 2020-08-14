package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ArrayList<Operacion> operaciones= new ArrayList<Operacion>();

    public static void main(String[] args) {
        ArrayList<Operacion> operaciones= new ArrayList<Operacion>();
        //   Ruta del archivo que se analizara
        String ruta = "prueba.txt";

        String contenido = leer(ruta);


        separar(contenido);
        imprimirResultados();
    }

    public static void separar(String contenido){
        String operacion = "";
        for (int i = 0; i < contenido.length(); i++){
            Operacion op = new Operacion();
            if (!contenido.substring(i,i+1).equals(";")){
                operacion += contenido.substring(i,i+1);
            }else{
                op = separaElementos(operacion);
                op.setTxtoperacion(operacion);
                operaciones.add(op);
                operacion = "";
            }
        }
    }

    public static String leer(String ruta){

        String contenido = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try{
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            int indexline = 0;
            List<String> remplazar = new ArrayList<>();
            System.out.println("\n                                      **********************************ERRORES**********************************");
            while((linea=br.readLine())!=null){
                String anterio = "";
                for (int i = 0; i < linea.length(); i++){
                    String a = linea.substring(i, i+1);
                    if(a.equals("/")|a.equals("*")|a.equals("+")|a.equals("-")){
                        if (anterio.equals("+")|anterio.equals("-")|anterio.equals("*")|anterio.equals("/")){
                            int ii = i - 1;
                            System.out.println("Hay dos operadores seguidos en la linea "+ indexline + " caracteres No. "+ i+", " + ii +" " + anterio + " "+ a);
                        }
                    }
                    if(!a.equals(" ")&&!a.equals("\n")&&!a.equals("\t")&&!a.equals(";")&&!isNumeric(a)&&!a.equals("/")&&!a.equals("*")&&!a.equals("+")&&!a.equals("-")&&!a.equals("(")&&!a.equals(")")){
                        System.out.println("Error encontrado en linea "+ indexline + " caracter No. " + i + " '"+a+"' No es un caracter valido");
                        remplazar.add(a);
                    }

                    anterio = a;
                }
                contenido += linea+"\n";
                indexline++;
            }
            br.close();
            fr.close();

            for (String s: remplazar){
                contenido = contenido.replace(s, "");
            }

        }catch(Exception e){
            System.out.println("Error al tratar de leer el archivo: " + e.getMessage());
        }finally{
            try {
                if(fr!=null){
                    fr.close();
                }
            } catch (Exception e) {
                System.out.println("No se pudo cerrar el archivo: "+ e.getMessage());
            }
        }

        String text = contenido;
        text = text.replace("\n", "").replace(";", ";\n").replace("\n\n", "\n").replace("\t", "").replace(" ", "");

        return text;

    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public static Operacion separaElementos(String ope){
        Operacion operacion = new Operacion();
        operacion.setTxtoperacion(ope);
        List<String> elemento = new ArrayList<>();
        String temp = "";
        String txtop = "";
        String t = "";
        for (int i = 0; i<ope.length();i++){
            t = ope.substring(i, i+1);
            if(i+1==ope.length()){
                if (isNumeric(t)){
                    temp += t;
                    elemento.add(temp);
                } else{
                    elemento.add(temp);
                    temp = "";
                    elemento.add(t);
                }
            }else if (isNumeric(t)){
                temp += t;
            } else{
                elemento.add(temp);
                temp = "";
                elemento.add(t);
            }
        }
        operacion.setElemento(elemento);


        for (String e: elemento){
            if (isNumeric(e)){
                txtop += "numero ";
            }else{
                switch(e) {
                    case "+":
                        txtop += "más ";
                        break;
                    case "-":
                        txtop += "menos ";
                        break;
                    case "*":
                        txtop += "por ";
                        break;
                    case "/":
                        txtop += "dividido ";
                        break;
                    case "(":
                        txtop += "parentesis_a ";
                        break;
                    case ")":
                        txtop += "parentesis_c ";
                        break;
                }

            }
        }

        operacion.setOutput1(txtop);


        return operacion;
    }

    public static void imprimirResultados(){
        System.out.println("\n                                      **********************************OUTPUTS**********************************");
        for (Operacion o: operaciones){
            System.out.print(o.getTxtoperacion()+ "------>" +o.getOutput1());
        }
    }


}
