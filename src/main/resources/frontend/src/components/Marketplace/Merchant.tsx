import "./Merchant.css"
import {Simulate} from "react-dom/test-utils";
import click = Simulate.click;

interface MerchantProps {
    id: number,
    handleActiveMerchant: (merchantId: number) => void
}

const Merchant = (props: MerchantProps) => {

    const focusMerchant = () => {
        props.handleActiveMerchant(props.id)
    }
    return (
        <div className="merchant" onClick={focusMerchant}>
        </div>
    );
}

export default Merchant;
