package be.helha.aemt.control;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import be.helha.aemt.ejb.MemberManagerEJB;
import be.helha.aemt.entity.FormerStudent;
import be.helha.aemt.entity.Member;
import be.helha.aemt.enumeration.GroupName;

@Named
@SessionScoped
public class AuthBean implements Serializable {
	private static final long serialVersionUID = -2154569754861903818L;

	@EJB
	private MemberManagerEJB gestion;

	private Member member;
	private String mail;

	/**
	 * Returns the authenticated member. Retrieves it from the FacesContext using
	 * it's mail if not initialised yet.
	 * 
	 * @return authenticated member, if authenticated.
	 */
	public Member selectMember() {
		String mailCurrent = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if ((mailCurrent != null && mail != mailCurrent) || member == null) {
			mail = mailCurrent;
			if (mailCurrent != null && mailCurrent.length() > 0) {
				Member retrieved = gestion.findByMail(mailCurrent);

				if (retrieved instanceof FormerStudent) {
					FormerStudent fs = (FormerStudent) retrieved;
					if (!fs.isApproved()) {
						return null;
					}
				}
				member = retrieved;
			}
		}

		return member;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		member = null;
		mail = null;

		return "index?faces-redirect=true";
	}

	public boolean hasAddress() {
		Member m = selectMember();
		if (m == null || !(m instanceof FormerStudent))
			return false;

		FormerStudent fs = (FormerStudent) m;
		return fs.getAddress() != null;
	}

	public boolean hasAdminRights() {
		if (selectMember() == null)
			return false;

		return selectMember().getGroupName() == GroupName.ADMIN;
	}

	public boolean isAuthenticated() {
		return selectMember() != null;
	}
}
