
package net.webservicex;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCountryByCountryCodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCountryByCountryCodeResult"
})
@XmlRootElement(name = "GetCountryByCountryCodeResponse")
public class GetCountryByCountryCodeResponse {

    @XmlElement(name = "GetCountryByCountryCodeResult")
    protected String getCountryByCountryCodeResult;

    /**
     * Gets the value of the getCountryByCountryCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetCountryByCountryCodeResult() {
        return getCountryByCountryCodeResult;
    }

    /**
     * Sets the value of the getCountryByCountryCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetCountryByCountryCodeResult(String value) {
        this.getCountryByCountryCodeResult = value;
    }

}
