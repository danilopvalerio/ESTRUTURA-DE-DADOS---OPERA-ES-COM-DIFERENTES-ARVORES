package arvoreAVL;

public class AVL<T> {
	private NodeAVL<T> raiz = null;

	private int rotacoesEsquerda = 0;
	private int rotacoesDireita = 0;
	

	public void setRaiz(NodeAVL<T> raiz) {
		this.raiz = raiz;
	}

	public int getRotacoesEsquerda() {
		return rotacoesEsquerda;
	}

	public void setRotacoesEsquerda(int rotacoesEsquerda) {
		this.rotacoesEsquerda = rotacoesEsquerda;
	}

	public int getRotacoesDireita() {
		return rotacoesDireita;
	}

	public void setRotacoesDireita(int rotacoesDireita) {
		this.rotacoesDireita = rotacoesDireita;
	}

	public void inserir(int chave){
		this.raiz = inserir(this.raiz, chave);
	}

	private int altura(NodeAVL<T> raiz){
		return (raiz != null ? raiz.getAltura() : 0);
	}

	private NodeAVL<T> inserir(NodeAVL<T> raiz, int chave){
		if(raiz == null){
			NodeAVL<T> node = new NodeAVL<T>(chave);
			return node;
		}
		else if(raiz.getChave() > chave){
			raiz.setEsq(inserir(raiz.getEsq(), chave));

			if(altura(raiz.getEsq()) - altura(raiz.getDir()) == 2){
				if(altura(raiz.getEsq().getEsq()) > altura(raiz.getEsq().getDir())){
					raiz = rotacaoDireita(raiz);

				}
				else{
					raiz = rotacaoDuplaDireita(raiz);

				}
			}
		}
		else if(raiz.getChave() < chave){
			raiz.setDir(inserir(raiz.getDir(), chave));

			if(altura(raiz.getDir()) - altura(raiz.getEsq()) == 2){
				if(altura(raiz.getDir().getDir()) > altura(raiz.getDir().getEsq())){

					raiz = rotacaoEsquerda(raiz);

				}
				else{
					raiz = rotacaoDuplaEsquerda(raiz);

				}
			}
		}

		raiz.setAltura(Math.max(altura(raiz.getDir()), altura(raiz.getEsq())) + 1);


		return raiz;
	}

	public NodeAVL<T> getRaiz() {
		return this.raiz;
	}

	private NodeAVL<T> rotacaoEsquerda(NodeAVL<T> node) {
		NodeAVL<T> aux = node.getDir();
		node.setDir(aux.getEsq());
		aux.setEsq(node);

		node.setAltura(Math.max(altura(node.getEsq()), altura(node.getDir())) + 1);
		aux.setAltura(Math.max(altura(aux.getEsq()), altura(aux.getDir())) + 1);
		rotacoesEsquerda++;
		return aux;
	}

	private NodeAVL<T> rotacaoDireita(NodeAVL<T> node) {
		NodeAVL<T> aux = node.getEsq();
		node.setEsq(aux.getDir());
		aux.setDir(node);

		node.setAltura(Math.max(altura(node.getEsq()), altura(node.getDir())) + 1);
		aux.setAltura(Math.max(altura(aux.getEsq()), altura(aux.getDir())) + 1);
		rotacoesDireita++;
		return aux;
	}

	private NodeAVL<T> rotacaoDuplaDireita(NodeAVL<T> node) {
		node.setEsq(rotacaoEsquerda(node.getEsq()));
		node = rotacaoDireita(node);

		return node;
	}

	private NodeAVL<T> rotacaoDuplaEsquerda(NodeAVL<T> node) {
		node.setDir(rotacaoDireita(node.getDir()));
		node = rotacaoEsquerda(node);
		return node;
	}

	public boolean buscar(int chave) {
		return buscar(this.raiz, chave);
	}

	private boolean buscar(NodeAVL<T> raiz, int chave) {
		if (raiz == null) {
			return false;
		}
		if (raiz.getChave() == chave) {
			return true;
		} else if (raiz.getChave() > chave) {
			return buscar(raiz.getEsq(), chave);
		} else {
			return buscar(raiz.getDir(), chave);
		}
	}

	public int alturaArvore() {
		return altura(this.raiz);
	}

}
