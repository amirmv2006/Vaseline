package ir.amv.os.vaseline.security.authorization.api.shared.criteria;

public interface ISecurityCriteria {
    ISecurityCriteria or(ISecurityCriteria criteria);
}
