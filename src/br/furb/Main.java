package br.furb;

public class Main {

    private static class Item {
        public int peso;
        public int valor;

        public Item (int peso, int valor) {
            this.peso = peso;
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        Item[] itens = new Item[3];
        itens[0] = new Item(2, 3);
        itens[1] = new Item(3, 6);
        itens[2] = new Item(6, 9);

        Integer[][] tabelaItens = programacaoDinamica(itens, 10);
        System.out.println(tabelaItens);
        Item[] items = pegarItens(tabelaItens, itens, 10);
        System.out.println(items);
    }

    private static Item[] pegarItens(Integer[][] tabelaItens, Item[] itens, int capacidadeMochila) {
        Item[] itensNaMochila = new Item[itens.length];
        int x = capacidadeMochila;
        for (int i = itens.length; i > 1; i--) {
            Item item = itens[i-1];
            if (tabelaItens[i][x] == tabelaItens[i-1][x-item.peso] + item.valor) {
                itensNaMochila[i-1] = item;
                x -= item.peso;
            }
        }
        return itensNaMochila;
    }

    private static Integer[][] programacaoDinamica(Item[] itens, int capacidadeMochila) {
        Integer[][] tabelaItens = new Integer[itens.length+1][capacidadeMochila+1];
        for (int i = 0; i <= capacidadeMochila; i++) {
            tabelaItens[0][i] = 0;
        }
        for (int i = 0; i <= itens.length; i++) {
            tabelaItens[i][0] = 0;
        }
        for (int i = 1; i <= itens.length; i++) {
            for (int x = 0; x <= capacidadeMochila; x++) {
                Integer pesoPedido = itens[i-1].peso;
                if (pesoPedido > x) {
                    tabelaItens[i][x] = tabelaItens[i-1][x];
                } else {
                    int valorTabela = itens[i-1].valor + (tabelaItens[i-1][x-pesoPedido]);
                    int valorTabelaNaoUsar = tabelaItens[i-1][x];
                    tabelaItens[i][x] = Integer.max(valorTabela, valorTabelaNaoUsar);
                }
            }
        }
        return tabelaItens;
    }


}
