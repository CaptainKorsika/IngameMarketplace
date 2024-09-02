import {ItemProps} from "./ItemProps";

interface PlayerObjectProps {
    name: string
    money: number
    inventorySpace: number
    inventoryItems: [ItemProps, number][]
    day: number
}