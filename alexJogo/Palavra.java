package jogoAlex;

public class Palavra {
	private String p;
	private String pkey;
	
	public Palavra(String p, String pkey) {
		this.p = p;
		this.pkey = pkey;
	}
	
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Palavra) {
			Palavra pal = (Palavra) o;
			if((pal.getP().equals(p)) && (pal.getPkey().equals(pkey))) {
				return true;
			}
		}
		return false;
	}

}
