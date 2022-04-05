<template>
    <footer>
        <p>&#169; {{FooterText}}</p>
    </footer> 
</template>

<script>
export default {
    data() {
        return {
            FooterText: '2022'
        }
    }
    
}
</script>

<style scoped>
    footer {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        background-color: lightgray;
        padding: 10px;
    }

</style>