package pidev.entity;

public class Vote {
	
	private int id;
	
	private int id_projet;
	
	private int id_user;
	
	

	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Vote(int id, int id_projet, int id_user) {
		super();
		this.id = id;
		this.id_projet = id_projet;
		this.id_user = id_user;
	}



	public Vote(int id_projet, int id_user) {
		super();
		this.id_projet = id_projet;
		this.id_user = id_user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_projet() {
		return id_projet;
	}

	public void setId_projet(int id_projet) {
		this.id_projet = id_projet;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}


	@Override
	public String toString() {
		return "Vote [id=" + id + ", id_projet=" + id_projet + ", id_user=" + id_user + "]";
	}
	
	
	
	

}
