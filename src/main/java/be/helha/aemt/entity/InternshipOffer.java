package be.helha.aemt.entity;

import java.util.Date;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import be.helha.aemt.enumeration.Major;

@Entity
@NamedQueries({
	@NamedQuery(name="Offer.queryInternship", query="SELECT o FROM InternshipOffer o ORDER BY o.postingDate ASC"),
	@NamedQuery(name="Offer.queryInternshipAmountToApprove", query="SELECT count(o) FROM InternshipOffer o WHERE o.approved = false")
})

public class InternshipOffer extends Offer implements Serializable{
	private static final long serialVersionUID = -7456281096807696752L;
	
	private String duration;
	private boolean thesisPossibility;

	public InternshipOffer() {
		super();
	}
	
	public InternshipOffer(String labelOffer, String companyName, String descriptionOffer, LocalDate startingDate,
			LocalDate postingDate, boolean approved, Major major, String duration, boolean thesisPossibility) {
		super(labelOffer, companyName, descriptionOffer, startingDate, postingDate, approved, major);
		this.duration = duration;
		this.thesisPossibility = thesisPossibility;
	}
	
	public InternshipOffer(String labelOffer, String companyName, String descriptionOffer, LocalDate startingDate,
			LocalDate postingDate, boolean approved, Major major, Address address, String duration,
			boolean thesisPossibility) {
		super(labelOffer, companyName, descriptionOffer, startingDate, postingDate, approved, major, address);
		this.duration = duration;
		this.thesisPossibility = thesisPossibility;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public boolean isThesisPossibility() {
		return thesisPossibility;
	}

	public void setThesisPossibility(boolean thesisPossibility) {
		this.thesisPossibility = thesisPossibility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + (thesisPossibility ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InternshipOffer other = (InternshipOffer) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (thesisPossibility != other.thesisPossibility)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", OffreStage [duree=" + duration + ", possibiliteTFE=" + thesisPossibility
				+ "]";
	}
	
	
}
