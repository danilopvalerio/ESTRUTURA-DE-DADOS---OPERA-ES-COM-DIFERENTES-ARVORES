package arvoreAVL;

public class NodeAVL<T> {
	private NodeAVL<T> esq;
	private NodeAVL<T> dir;
	private int altura;
	private int chave;
	
	public NodeAVL(int chave){
		esq = null;
		dir = null;
		altura = 1;
		this.chave = chave;
	}

	public NodeAVL<T> getEsq() {
		return esq;
	}

	public void setEsq(NodeAVL<T> esq) {
		this.esq = esq;
	}

	public NodeAVL<T> getDir() {
		return dir;
	}

	public void setDir(NodeAVL<T> dir) {
		this.dir = dir;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	
}
