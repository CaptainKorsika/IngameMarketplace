export type ItemObject = {
    item: {
        name: string
        image: string
        averageMerchantPrice: string
        currentPrice: string
    },
    amount: number,
    averageBuyingPrice: string | null
}