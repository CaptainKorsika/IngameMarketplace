import "./Marketplace.css"
import Merchant from "./Merchant";

interface MarketplaceProps {
    handleActiveMerchant: (merchantId: number) => void
}

const Marketplace = (props: MarketplaceProps) => {
    return (
        <div className="marketplace">
            <Merchant id={1} handleActiveMerchant={props.handleActiveMerchant}/>
            <Merchant id={2} handleActiveMerchant={props.handleActiveMerchant}/>
            <Merchant id={3} handleActiveMerchant={props.handleActiveMerchant}/>
        </div>
    );
}

export default Marketplace;
