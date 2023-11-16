package belskii.artem.bulletinboard.dao.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER_LIST",
		uniqueConstraints=@UniqueConstraint(columnNames = {"email"}))
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 3, max = 100)
	private String firstName;
	@NotNull
	@Size(min = 3, max = 100)
	private String lastNamer;
	@NotNull
	@Size(min = 5, max = 100)
	private String email;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastNamer() {
		return lastNamer;
	}
	public void setLastNamer(String lastNamer) {
		this.lastNamer = lastNamer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
